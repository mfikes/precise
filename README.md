# precise

Tagged literals for [exact](https://github.com/gfredericks/exact).

## Usage

`deps.edn`:

```clojure
{:deps {org.clojure/clojurescript {:mvn/version "1.10.339"}
        github-mfikes/precise {:git/url "https://github.com/mfikes/precise"
                               :sha "19587e7d26cf367356a2c0615015b2de428f4bf2"}}}
```                 

Start a REPL using
```
clj -i @precise/tagged_literals.cljc -m cljs.main -re node -r
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
