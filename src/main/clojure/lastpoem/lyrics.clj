(ns lastpoem.lyrics
  (:require [net.cgrand.enlive-html :as html]))

(def *lyricswiki-api-base-uri* "http://lyricwiki.org/api.php?")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn lyricswiki-query
  "Returns the URI to the page on lyrics wiki that contains the lyrics for a song, or nil if not found."
  [artist song]
  (let [find-first (fn [pred seq] (first (filter pred seq)))
	get-tag (fn [doc tag] (->> doc
				   :content
				   (find-first #(= (:tag %) tag))
				   :content
				   first))
	result (clojure.xml/parse (str *lyricswiki-api-base-uri*
				       "artist=" artist
				       "&song=" song
				       "&fmt=xml"))
	snippet (get-tag result :lyrics)]
    (if (= snippet "Not found") nil
	(get-tag result :url))))

(defn extract-lyrics
  "Extracts the actual lyrics from a lyrics wiki page. Removes blank lines."
  [doc]
  (loop [nodes (:content (first (html/select doc [:div.lyricbox])))
	 acc ""
	 last-newline false]
    (if-let [node (first nodes)]
      (let [[new-str newline] (cond (string? node) [node false]
				    (= (:tag node) :br) [(if last-newline nil "\n") true]
				    :else [nil last-newline])]
	(recur (rest nodes)
	       (str acc new-str)
	       newline))
      acc)))

