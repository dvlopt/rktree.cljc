(ns user

  "For daydreaming in the REPL." 

  (:require [cognitect.transit          :as transit]
            [dvlopt.rktree              :as rktree]
            [dvlopt.rktree.dev          :as rktree.dev]
            [dvlopt.rktree.transit      :as rktree.transit]
            [dvlopt.void                :as void]))


;;;;;;;;;;


(comment

  ;; Examples from README


  (def my-tree
       (sorted-map 0 (sorted-map 1 {:a {:b 'leaf-1}})
                   5 {:a {:d {:e 'leaf-2}}}))


  (= 'leaf-1
     (rktree/get my-tree
                 [0 1]
                 [:a :b]))


  (= (rktree/pop my-tree)

     [(sorted-map 5 {:a {:d {:e 'leaf-2}}})
      [0 1]
      {:a {:b 'leaf-1}}])


  (def my-tree-2
       (rktree/assoc my-tree
                     [0 1 0 0 0 5]
                     [:possible?]
                     true))

  ;; Notice that 'leaf has been re-prioritized from [0 1] to [0 1 0 0 0 0].
  ;; Order is actuall ymaintained as before, but we can account for the new
  ;; addition above.

  (= 'leaf-1
     (rktree/get my-tree-2
                 [0 1 0 0 0 0]
                 [:a :b]))

  ;; But notice that we can still use the original ranks!

  (= 'leaf-1
     (rktree/get my-tree-2
                 [0 1]
                 [:a :b]))
  )
