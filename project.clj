(defproject lastpoem "0.0.1"
  :description "The Last Poem"
  :namespaces [lastpoem.app_servlet]
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.5.1"]
                 [enlive "1.0.0-SNAPSHOT"]]
  :dev-dependencies [[appengine-magic "0.2.1-SNAPSHOT"]
                     [swank-clojure "1.2.1"]]
  :test-resources-path "resources/war")