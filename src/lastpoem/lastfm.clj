(ns lastpoem.lastfm
  (:require [lastpoem.util :as util]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.contrib.zip-filter.xml :as zip-xml])
  (:gen-class :main false))

(def lastfm-base-url "http://ws.audioscrobbler.com/2.0/")
(def lastfm-api-key "fc2467833ca37805a9e5e7f0664b61fc")

(defn- ziplist [keys vals]
  (map #(list %1 %2) keys vals))

(def initial-number-of-tracks 15)
(defn recent-tracks [username]
  (let [tracks (zip/xml-zip
                (xml/parse
                 (util/rest-url lastfm-base-url
                                {"method" "user.getrecenttracks"
                                 "user" username
                                 "limit" initial-number-of-tracks
                                 "api_key" lastfm-api-key})))]
    (ziplist (zip-xml/xml-> tracks :recenttracks :track :artist zip-xml/text)
             (zip-xml/xml-> tracks :recenttracks :track :name zip-xml/text))))
