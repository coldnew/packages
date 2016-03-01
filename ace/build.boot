(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.5.0" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.2.3")
(def ace-checksum "9CA426DEEFC00E7E3D0A3A715924D485")

(def +version+ +lib-version+)

(task-options!
  pom  {:project     'cljsjs/ace
        :version     +version+
        :scm         {:url "https://github.com/cljsjs/packages"}
        :description "Ace (Ajax.org Cloud9 Editor)"
        :url         "https://ace.c9.io/"
        :license     {"BSD" "https://github.com/ajaxorg/ace/blob/master/LICENSE"}})

(require '[boot.core :as c]
         '[boot.tmpdir :as tmpd]
         '[clojure.java.io :as io]
         '[clojure.string :as string]
         '[boot.util :refer [sh]]
         '[boot.tmpdir :as tmpd])

(deftask package []
  (comp
    (download :url (format "https://github.com/ajaxorg/ace/archive/v%s.zip" +lib-version+)
              :unzip true
              :checksum ace-checksum)
    ;; (sift :move {#"^CodeMirror-([\d\.]*)/lib/codemirror\.js"    "cljsjs/codemirror/development/codemirror.inc.js"
    ;;              #"^CodeMirror-([\d\.]*)/lib/codemirror\.css"   "cljsjs/codemirror/development/codemirror.css"
    ;;              #"^CodeMirror-([\d\.]*)/mode/(.*)/\2\.js"      "cljsjs/codemirror/common/mode/$2.js"
    ;;              #"^CodeMirror-([\d\.]*)/keymap/(.*)\.js"       "cljsjs/codemirror/common/keymap/$2.js"
    ;;              #"^CodeMirror-([\d\.]*)/addon/(.*)/(.*)\.css"  "cljsjs/codemirror/common/addon/$2/$3.css"
    ;;              #"^CodeMirror-([\d\.]*)/addon/(.*)/(.*)\.js"   "cljsjs/codemirror/common/addon/$2/$3.js"
    ;;              #"^CodeMirror-([\d\.]*)/theme/(.*)\.css"       "cljsjs/codemirror/common/theme/$2.css"})
    ;; (minify    :in       "cljsjs/codemirror/development/codemirror.inc.js"
    ;;            :out      "cljsjs/codemirror/production/codemirror.min.inc.js")
    ;; (minify    :in       "cljsjs/codemirror/development/codemirror.css"
    ;;            :out      "cljsjs/codemirror/production/codemirror.min.css")
    ;; (sift :include #{#"^cljsjs"})
    ;; (deps-cljs :name "cljsjs.codemirror")
    ;; (sift :move {#"^cljsjs/codemirror/common/mode/(.*)\.js" "cljsjs/codemirror/common/mode/$1.inc.js"
    ;;              #"^cljsjs/codemirror/common/keymap/(.*)\.js" "cljsjs/codemirror/common/keymap/$1.inc.js"
    ;;              #"^cljsjs/codemirror/common/addon/(.*)/(.*)\.js" "cljsjs/codemirror/common/addon/$1/$2.inc.js"})
    ))