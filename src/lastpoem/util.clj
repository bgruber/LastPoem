(ns lastpoem.util
  (:require [appengine-magic.services.urlfetch :as urlfetch]
            [clojure.string :as string])
  (:import java.net.URLEncoder))

(defn rest-url [baseUrl params]
  (str baseUrl
       "?"
       (string/join "&" (map #(str %1 "=" (URLEncoder/encode (str %2) "UTF-8"))
                             (keys params)
                             (vals params)))))

(defn fetch-result-to-stream
  [^com.google.appengine.api.urlfetch.HTTPResponse r]
  (java.io.ByteArrayInputStream. (:content r)))


(defn fetch-url
  "Get an InputStream representing the url."
  [url]
  (fetch-result-to-stream (urlfetch/fetch url)))
