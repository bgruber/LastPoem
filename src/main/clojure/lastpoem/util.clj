(ns lastpoem.util
  (:require [clojure.string :as string])
  (:import java.net.URLEncoder))

(defn rest-url [baseUrl params]
  (str baseUrl
       "?"
       (string/join "&" (map #(str %1 "=" (URLEncoder/encode (str %2) "UTF-8"))
			  (keys params)
			  (vals params)))))
