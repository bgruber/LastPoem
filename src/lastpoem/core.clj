(ns lastpoem.core
  (:use [compojure.core])
  (:require [appengine-magic.core :as ae]))

(defroutes lastpoem-app-handler
  (GET "/" [] "Hello, world!"))

(ae/def-appengine-app lastpoem-app #'lastpoem-app-handler)