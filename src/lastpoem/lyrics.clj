(ns lastpoem.lyrics
  (:use lastpoem.util)
  (:require [net.cgrand.enlive-html :as html]))

(def lyricswiki-api-base-uri "http://lyricwiki.org/api.php")

(defn lyricswiki-query
  "Returns the URI to the page on lyrics wiki that contains the lyrics for a song, or nil if not found."
  [artist song]
  (let [find-first (fn [pred seq] (first (filter pred seq)))
		get-tag (fn [doc tag] (->> doc
								   :content
								   (find-first #(= (:tag %) tag))
								   :content
								   first))
        result-stream (fetch-url (rest-url lyricswiki-api-base-uri
                                           {"artist" artist
                                            "song" song
                                            "fmt" "xml"}))
		result (clojure.xml/parse result-stream)
		snippet (get-tag result :lyrics)]
    (if (= snippet "Not found") nil
		(get-tag result :url))))

(defn extract-lyrics
  "Extracts the actual lyrics from a lyrics wiki page."
  [doc]
  (filter string? (:content (first (html/select doc [:div.lyricbox])))))

(defn fetch-lyrics [artist song]
  (let [url (lyricswiki-query artist song)]
	(if-not (nil? url)
      (extract-lyrics (html/html-resource (fetch-url url))))))

#_
{:tag :div,
 :attrs {:class "lyricbox"},
 :content
 ({:tag :div,
   :attrs {:class "rtMatcher"},
   :content
   ({:tag :a,
     :attrs
     {:target "_blank",
      :rel "nofollow",
      :href
      "http://www.ringtonematcher.com/co/ringtonematcher/02/noc.asp?sid=WILWros&artist=Santogold&song=L.E.S%2BArtistes"},
     :content
     ({:tag :img,
       :attrs
       {:height "17",
        :width "16",
        :alt "phone",
        :src
        "http://images.wikia.com/lyricwiki/images/phone_left.gif"},
       :content nil}
      " Send \"L.E.S Artistes\" Ringtone to your Cell "
      {:tag :img,
       :attrs
       {:height "17",
        :width "16",
        :alt "phone",
        :src
        "http://images.wikia.com/lyricwiki/images/phone_right.gif"},
       :content nil})})}
  "What I'm searching for"
  {:tag :br, :attrs nil, :content nil}
  "To tell it straight, I'm trying to build a wall"
  {:tag :br, :attrs nil, :content nil}
  "Walking by myself"
  {:tag :br, :attrs nil, :content nil}
  "Down avenues that reek of time to kill"
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  "If you see me, keep going"
  {:tag :br, :attrs nil, :content nil}
  "Be a pass-by waver"
  {:tag :br, :attrs nil, :content nil}
  "Build me up, bring me down"
  {:tag :br, :attrs nil, :content nil}
  "Just leave me out, you name-dropper"
  {:tag :br, :attrs nil, :content nil}
  "Stop trying to catch my eye"
  {:tag :br, :attrs nil, :content nil}
  "I see you good, you forced faker"
  {:tag :br, :attrs nil, :content nil}
  "Just make it easy"
  {:tag :br, :attrs nil, :content nil}
  "You're my enemy, you fast-talker"
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  {:tag :b,
   :attrs nil,
   :content
   ("I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe"
    {:tag :br, :attrs nil, :content nil}
    "I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe")}
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  "What am I here for?"
  {:tag :br, :attrs nil, :content nil}
  "I left my home to disappear is all"
  {:tag :br, :attrs nil, :content nil}
  "I'm here for myself"
  {:tag :br, :attrs nil, :content nil}
  "Not to know you, I don't need no one else"
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  "Fit in so good the hope is"
  {:tag :br, :attrs nil, :content nil}
  "That you cannot see me later"
  {:tag :br, :attrs nil, :content nil}
  "You don't know me "
  {:tag :br, :attrs nil, :content nil}
  "I am an introvert, an excavator"
  {:tag :br, :attrs nil, :content nil}
  "I'm duckin' out for now"
  {:tag :br, :attrs nil, :content nil}
  "A face in dodgy elevators"
  {:tag :br, :attrs nil, :content nil}
  "Creep up and suddenly"
  {:tag :br, :attrs nil, :content nil}
  "I found myself an innovator"
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  {:tag :b,
   :attrs nil,
   :content
   ("I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe"
    {:tag :br, :attrs nil, :content nil}
    "I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe")}
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  "Change, change, change, change"
  {:tag :br, :attrs nil, :content nil}
  "I want to get up out of my skin"
  {:tag :br, :attrs nil, :content nil}
  "Tell you what, if I can shake it"
  {:tag :br, :attrs nil, :content nil}
  "I'm-a make this, something worth dreaming of "
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  {:tag :b,
   :attrs nil,
   :content
   ("I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe"
    {:tag :br, :attrs nil, :content nil}
    "I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe")}
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  {:tag :b,
   :attrs nil,
   :content
   ("I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe"
    {:tag :br, :attrs nil, :content nil}
    "I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe")}
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  {:tag :b,
   :attrs nil,
   :content
   ("I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe"
    {:tag :br, :attrs nil, :content nil}
    "I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe")}
  {:tag :br, :attrs nil, :content nil}
  {:tag :br, :attrs nil, :content nil}
  {:tag :b,
   :attrs nil,
   :content
   ("I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe"
    {:tag :br, :attrs nil, :content nil}
    "I can say I'll hope it will be worth what I give up"
    {:tag :br, :attrs nil, :content nil}
    "If I could stand up mean for the things that I believe")}
  {:type :comment,
   :data
   " \n<p>NewPP limit report\nPreprocessor node count: 135/1000000\nPost-expand include size: 1788/2097152 bytes\nTemplate argument size: 282/2097152 bytes\nExpensive parser function count: 2/100\nExtLoops count: 0/100\n</p>\n"}
  {:tag :div,
   :attrs {:class "rtMatcher"},
   :content
   ({:tag :a,
     :attrs
     {:target "_blank",
      :rel "nofollow",
      :href
      "http://www.ringtonematcher.com/co/ringtonematcher/02/noc.asp?sid=WILWros&artist=Santogold&song=L.E.S%2BArtistes"},
     :content
     ({:tag :img,
       :attrs
       {:height "17",
        :width "16",
        :alt "phone",
        :src
        "http://images.wikia.com/lyricwiki/images/phone_left.gif"},
       :content nil}
      " Send \"L.E.S Artistes\" Ringtone to your Cell "
      {:tag :img,
       :attrs
       {:height "17",
        :width "16",
        :alt "phone",
        :src
        "http://images.wikia.com/lyricwiki/images/phone_right.gif"},
       :content nil})})})}