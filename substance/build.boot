(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.5.1" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.0.0-beta.3")
(def substance-checksum "75A6DEA71FA723FD3E18548217F55A74")

(def +version+ +lib-version+)

(task-options!
  pom  {:project     'cljsjs/substance
        :version     +version+
        :scm         {:url "https://github.com/cljsjs/packages"}
        :description "Substance is a JavaScript library for web-based content editing."
        :url         "http://substance.io"
        :license     {"MIT" "https://github.com/substance/substance/blob/master/LICENSE"}})

(deftask package []
  (comp
   (download :url (format "https://github.com/substance/substance/archive/v%s.zip" +lib-version+)
             :unzip true
             :checksum substance-checksum)
   (sift :move {#"^substance-([\d\.]*).*/model/(.*)/\2\.js"     "cljsjs/substance/model/$2.js"
                #"^substance-([\d\.]*).*/packages/(.*)/(.*)\2\.js"  "cljsjs/substance/packages/$2.js"
                })
   (sift :include #{#"^cljsjs"})
   (deps-cljs :name "cljsjs.substance")

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
   ;; (generate-extra-deps)
   ))