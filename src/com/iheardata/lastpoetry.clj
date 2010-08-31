(ns com.iheardata.lastpoetry
  (:use clojure.xml)
  (:use clojure.contrib.zip-filter.xml)
  (:require [clojure.zip :as zip])
  (:require [clojure.http.resourcefully :as resourcefully])
  (:use [clojure.contrib.str-utils :only (str-join)])
  (:import java.net.URLEncoder)
  (:gen-class :main false))

(defn rest-url [baseUrl params]
  (str baseUrl
       "?"
       (str-join "&" (map #(str %1 "=" (URLEncoder/encode (str %2) "UTF-8"))
			  (keys params)
			  (vals params)))))

(def lastfm-base-url "http://ws.audioscrobbler.com/2.0/")
(def lastfm-api-key "fc2467833ca37805a9e5e7f0664b61fc")

(def lyricwiki-base-url "http://lyricwiki.org/api.php")
(def initial-number-of-tracks 15)
(defn recent-tracks [username]
  (let [tracks (zip/xml-zip (parse (rest-url lastfm-base-url {"method" "user.getrecenttracks"
							      "user" username
							      "limit" initial-number-of-tracks
							      "api_key" lastfm-api-key})))]
    (zipmap (xml-> tracks :recenttracks :track :artist text)
	    (xml-> tracks :recenttracks :track :name text))))

(defn fetch-lyrics [artist song]
  (remove #(= "" %) ((resourcefully/get (rest-url lyricwiki-base-url {"func" "getSong",
								      "artist" artist,
								      "song" song,
								      "fmt" "text"})) :body-seq)))

(defn make-poetry [username]
  (let [lyriclist (remove #(= '("Not found") %)
			  (pmap #(apply fetch-lyrics %) (recent-tracks username)))
	successful (count lyriclist)
	nth-or-not (fn [n available]
		     (if (= n (dec successful))
		       (dec available)
		       (if (< n (dec available))
			 n
			 (- available (- successful n)))))]
    (map (fn [n lyrics]
	   (nth lyrics (nth-or-not n (count lyrics))))
	 (range successful) lyriclist)))
