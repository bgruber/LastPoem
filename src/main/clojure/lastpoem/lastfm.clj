(ns lastpoem.lastfm
  (:use clojure.xml
	clojure.contrib.zip-filter.xml)
  (:require [clojure.zip :as zip]
	    [clojure-http.resourcefully :as resourcefully])
  (:gen-class :main false))

(def lastfm-base-url "http://ws.audioscrobbler.com/2.0/")
(def lastfm-api-key "fc2467833ca37805a9e5e7f0664b61fc")

(def initial-number-of-tracks 15)
(defn recent-tracks [username]
  (let [tracks (zip/xml-zip (parse (rest-url lastfm-base-url {"method" "user.getrecenttracks"
							      "user" username
							      "limit" initial-number-of-tracks
							      "api_key" lastfm-api-key})))]
    (zipmap (xml-> tracks :recenttracks :track :artist text)
	    (xml-> tracks :recenttracks :track :name text))))
