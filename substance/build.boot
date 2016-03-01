(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.5.0" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.0.0-beta.3")
(def substance-checksum "")

(def +version+ +lib-version+)

(task-options!
  pom  {:project     'cljsjs/substance
        :version     +version+
        :scm         {:url "https://github.com/cljsjs/packages"}
        :description "Substance is a JavaScript library for web-based content editing."
        :url         "http://substance.io"
        :license     {"MIT" "https://github.com/substance/substance/blob/master/LICENSE"}})
