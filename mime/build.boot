(set-env!
 :resource-paths #{"resources"}
 :dependencies '[[cljsjs/boot-cljsjs "0.5.0" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.3.4")
(def mime-checksum "39FF4F73BCEF3379BC580CD5F00FF4B9")

(def +version+ +lib-version+)

(task-options!
 pom  {:project     'cljsjs/mime
       :version     +version+
       :scm         {:url "https://github.com/cljsjs/packages"}
       :description "A super simple utility library for dealing with mime-types."
       :url         "https://github.com/broofa/node-mime"
       :license     {"MIT" "https://github.com/broofa/node-mime/blob/master/LICENSE"}})

(require '[boot.core :as c]
         '[boot.tmpdir :as tmpd]
         '[clojure.java.io :as io]
         '[clojure.string :as string]
         '[boot.util :refer [sh]]
         '[boot.tmpdir :as tmpd])

(deftask package []
  (comp
    (download :url (format "https://github.com/broofa/node-mime/archive/v%s.zip" +lib-version+)
              :unzip true
              :checksum mime-checksum)
    (sift :move {#"^node-mime-([\d\.]*)/mime.js"    "cljsjs/mime/development/mime.inc.js"
                 #"^node-mime-([\d\.]*)/type.json"  "cljsjs/mime/development/type.json"})
    (minify    :in       "cljsjs/mime/development/mime.inc.js"
               :out      "cljsjs/mime/production/mime.min.inc.js")
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.mime")))