(defproject com.stuartsierra/component "0.2.2.x1-SNAPSHOT"
  :description "Managed lifecycle of stateful objects"
  :url "https://github.com/stuartsierra/component"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :min-lein-version "2.1.3"  ; added :global-vars
  :dependencies [[com.stuartsierra/dependency "0.1.2.x1-SNAPSHOT"]]
  :global-vars {*warn-on-reflection* true}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.5.1"]
                                  [org.clojure/clojurescript "0.0-2197"]
                                  [org.clojure/tools.namespace "0.2.4"]]
                   :source-paths ["dev"]}
             :clj1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}}
  :plugins [[com.keminglabs/cljx "0.3.2" :exclusions [org.clojure/clojure]]
            [lein-cljsbuild "1.0.3"]
            [com.cemerick/clojurescript.test "0.3.0"]]
  :hooks [leiningen.cljsbuild cljx.hooks]
  :source-paths ["target/gen/src"]
  :test-paths ["target/gen/test"]
  :cljx {:builds [{:source-paths ["src"]  :output-path "target/gen/src"  :rules :clj}
                  {:source-paths ["src"]  :output-path "target/gen/src"  :rules :cljs}
                  {:source-paths ["test"] :output-path "target/gen/test" :rules :clj}
                  {:source-paths ["test"] :output-path "target/gen/test" :rules :cljs}]}
  :cljsbuild {:builds {:dev {:source-paths ["target/gen/src"]
                             :compiler {:output-to "target/gen/js/main.js"
                                        :output-dir "target/gen/js"
                                        :optimizations :whitespace
                                        :pretty-print true
                                        :source-map "target/gen/js/main.js.map"}}
                       :test {:source-paths ["target/gen/src" "target/gen/test"]
                              :compiler {:output-to "target/gen/test/js/testable.js"
                                         ;; :optimizations :advanced
                                         :optimizations :simple
                                         :pretty-print true}}}
              :test-commands {"phantomjs" ["phantomjs"
                                           :runner
                                           "bind-polyfill.js"
                                           "target/gen/test/js/testable.js"]}})
