(ns lastpoem.core
  (:use [compojure.core])
  (:require [appengine-magic.core :as ae]
            [clojure.contrib.json :as json]
            [compojure.route :as route]
            [lastpoem.poetry]
            [net.cgrand.enlive-html :as html]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/json-str data)})

(html/deftemplate poem-page "index.html"
  [user]
  [:div#poemArea] (html/html-content
                   (->> (lastpoem.poetry/make-poetry user)
                       (map #(str % "<br />"))
                       (apply str))))

(defroutes lastpoem-app-handler
  (GET "/poetry" {{:strs [user]} :params}
       (json-response (lastpoem.poetry/make-poetry user)))
  (GET "/poem-page" {{:strs [user]} :params}
       (poem-page user))
  (route/not-found "404"))

(ae/def-appengine-app lastpoem-app #'lastpoem-app-handler)
