(ns precise.tagged-literals
  (:require
   [clojure.string :as string]
   [com.gfredericks.exact :as exact]
   #?(:cljs [com.gfredericks.exact.impl :refer [Ratio]])
   #?(:cljs [goog.math.Integer])))

(defn read-integer [s]
  `(exact/string->integer ~s))

#?(:cljs
   (extend-type goog.math.Integer
     IPrintWithWriter
     (-pr-writer [o writer _]
       (-write writer (str "#exact/integer \"" (exact/integer->string o) "\"")))))

(defn read-ratio [s]
  (let [[n d] (string/split s #"/")]
    `(exact// (exact/string->integer ~n) (exact/string->integer ~d))))

#?(:cljs
   (extend-type Ratio
     IPrintWithWriter
     (-pr-writer [ratio writer _]
       (-write writer (str "#exact/ratio \""
                        (exact/integer->string (exact/numerator ratio))
                        "/"
                        (exact/integer->string (exact/denominator ratio))
                        "\"")))))
