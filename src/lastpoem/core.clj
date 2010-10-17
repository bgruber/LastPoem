(ns lastpoem.core
  (:use [compojure.core])
  (:require [appengine-magic.core :as ae]
            [clojure.contrib.json :as json]
            [compojure.route :as route]
            [lastpoem.poetry]))


(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/json-str data)})

(defroutes lastpoem-app-handler
  (GET "/poetry" {{:strs [user]} :params}
       (json-response (lastpoem.poetry/make-poetry user)))
  (route/not-found "404"))

(ae/def-appengine-app lastpoem-app #'lastpoem-app-handler)
