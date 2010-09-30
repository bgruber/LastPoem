(ns lastpoem.util
  (:require [clojure.string :as string])
  (:import java.net.URLEncoder))

(defn rest-url [baseUrl params]
  (str baseUrl
       "?"
       (string/join "&" (map #(str %1 "=" (URLEncoder/encode (str %2) "UTF-8"))
			  (keys params)
			  (vals params)))))

(defn fetch-url
  "Get an InputStream representing the url."
  [url]
  (.openStream (.toURL (java.net.URI. url))))

(defn fetch-urls [urls cont]
  (cont (doall (map deref (map #(future (fetch-url %))
                               urls)))))
