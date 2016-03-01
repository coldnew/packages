(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.5.0" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "0.0.3")
(def ammo-checksum "1D6A1145640B972DBBFB4EA60E89F4C5")

(def +version+ +lib-version+)

(task-options!
  pom  {:project     'cljsjs/ammo
        :version     +version+
        :scm         {:url "https://github.com/cljsjs/packages"}
        :description "Direct port of the Bullet physics engine to JavaScript using Emscripten"
        :url         "https://github.com/kripken/ammo.js/"
        :license     {"zlib" "https://github.com/kripken/ammo.js/blob/master/LICENSE"}})

(require '[boot.core :as c]
         '[boot.tmpdir :as tmpd]
         '[clojure.java.io :as io]
         '[clojure.string :as string]
         '[boot.util :refer [sh]]
         '[boot.tmpdir :as tmpd])

(deftask package []
  (comp
   (download :url (format "https://github.com/kripken/ammo.js/archive/%s.zip" +lib-version+)
             :unzip true
             :checksum ammo-checksum)
   (sift :move {#"^ammo.js-([\d\.]*)/builds/ammo.js"  "cljsjs/ammo/development/ammo.inc.js"})
   (minify    :in       "cljsjs/ammo/development/ammo.inc.js"
              :out      "cljsjs/ammo/production/ammo.min.inc.js")
   (sift :include #{#"^cljsjs"})
   (deps-cljs :name "cljsjs.ammo")))