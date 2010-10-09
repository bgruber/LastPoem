(ns lastpoem.core
  (:use [compojure.core])
  (:require [appengine-magic.core :as ae]
            [clojure.pprint]
            [compojure.route :as route]
            [lastpoem.poetry]))

(def pprint #(clojure.pprint/write % :stream nil))

(defroutes lastpoem-app-handler
  (GET "/poetry" []
       (pprint (lastpoem.poetry/make-poetry "bgruber")))
  (route/not-found "404"))

(ae/def-appengine-app lastpoem-app #'lastpoem-app-handler)
