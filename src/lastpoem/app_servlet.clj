(ns lastpoem.app_servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use lastpoem.core)
  (:use [appengine-magic.servlet :only [make-servlet-service-method]]))


(defn -service [this request response]
  ((make-servlet-service-method lastpoem-app) this request response))
