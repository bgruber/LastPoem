(ns lastpoem.lyrics
  (:use lastpoem.util)
  (:require [net.cgrand.enlive-html :as html]))

(def lyricswiki-api-base-uri "http://lyricwiki.org/api.php")

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
		result (clojure.xml/parse (rest-url lyricswiki-api-base-uri
											{"artist" artist
											 "song" song
											 "fmt" "xml"}))
		snippet (get-tag result :lyrics)]
    (if (= snippet "Not found") nil
		(get-tag result :url))))

(defn extract-lyrics
  "Extracts the actual lyrics from a lyrics wiki page."
  [doc]
  (filter string? (:content (first (html/select doc [:div.lyricbox])))))

(defn fetch-lyrics [artist song]
  (let [url (lyricswiki-query artist song)]
	(if-not (nil? url)
	 (extract-lyrics (fetch-url url)))))