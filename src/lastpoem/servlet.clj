(ns com.iheardata.lastpoem.servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use compojure.http
	compojure.html
	[com.iheardata.lastpoem.poetry :only (make-poetry)]))

(defn poetry-output [params]
  (map #(html % [:br]) (make-poetry (params :user))))

(defroutes poetry
  (GET "/poetry"
       (poetry-output params))
  (GET "/"
       (html
	[:html
	 [:head
	  [:title "Hello world"]]
	 [:body
	  [:div "hello world!"]]])))

(defservice poetry)