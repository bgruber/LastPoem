(ns lastpoem.poetry
  [:use lastpoem.lyrics
   lastpoem.lastfm])

(defn make-poetry [username]
  (let [lyriclist (remove nil?
                          (map #(apply fetch-lyrics %) (recent-tracks username)))
        successful (count lyriclist)
        nth-or-not (fn [n available]
                     (if (= n (dec successful))
                       (dec available)
                       (if (< n (dec available))
                         n
                         (mod (- available (- successful n)) available))))]
    (map (fn [n lyrics]
           (nth lyrics (nth-or-not n (count lyrics))))
         (range successful) lyriclist)))
