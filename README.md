# precise

Tagged literals for [exact](https://github.com/gfredericks/exact).

[![Clojars Project](https://img.shields.io/clojars/v/precise.svg)](https://clojars.org/precise)

## Usage

`deps.edn`:

```clojure
{:deps {org.clojure/clojurescript {:mvn/version "1.10.339"}
        precise {:mvn/version "0.1.0"}}}
```                 

Start a ClojureScript REPL using

```
clj -i @precise/tagged_literals.cljc -m cljs.main
```

or a Lumo REPL using

```
lumo -c `clojure -Spath`
```

Require the tagged literals and exact namespace:

```
cljs.user=> (require 'precise.tagged-literals 
                     '[com.gfredericks.exact :as e])
```

Try using `#exact/integer` and `#exact/ratio`:

```
cljs.user=> #exact/integer "2319871239717122424323214112455623"
#exact/integer "2319871239717122424323214112455623"
cljs.user=> #exact/ratio "2/3"
#exact/ratio "2/3"
cljs.user=> (e/numerator *1)
#exact/integer "2"
```

> Note that, even though the `#exact/integer` tagged literal works in terms of an arbitrary-length string of base-10 digits, the underlying data is a `goog.math.Integer` instance, which is efficiently implemented using an array of 32-bit signed pieces. Similarly, `#exact/ratio` affords a string-based literal representation of an implementation that involves a pair of such integers.

Newtonian square root [example](https://github.com/gfredericks/exact/blob/master/README.md#usage):

```
cljs.user=> (def TWO #exact/integer "2")
#'cljs.user/TWO
cljs.user=> (defn square [x] (e/* x x))
#'cljs.user/square
cljs.user=> (defn avg [a b] (-> a (e/+ b) (e// TWO)))
#'cljs.user/avg
cljs.user=> (defn newtonian-square-root
              "Returns a ratio between (- (sqrt x) epsilon) and (+ (sqrt x) epsilon)"
              [x epsilon]
              (let [ee (square epsilon)]
                (loop [guess (avg x e/ZERO)]
                  (if (-> guess square (e/- x) e/abs (e/< ee))
                    guess
                    (recur (-> x (e// guess) (avg guess)))))))
#'cljs.user/newtonian-square-root
cljs.user=> (def epsilon (e// #exact/integer "1000000000000000000000000"))
#'cljs.user/epsilon
cljs.user=> epsilon
#exact/ratio "1/1000000000000000000000000"
cljs.user=> (newtonian-square-root TWO epsilon)
#exact/ratio "1572584048032918633353217/1111984844349868137938112"
```
