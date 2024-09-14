**Assignment 2 - SWEN301**
-

The aim of this project was to extend a real world software system and to select,assess and use an existing open source component (log4j version 1.2.17).

a. There are no special requirements needed to run the main class (LogRunner), however once the 2 minutes are over you should press "Enter" in order to exit.

**JSON Library Selection**


b. For this assignment, two of the main dependencies/library selections were the log4j version 12.1.17, and the Gson library for JSON processing. These are some of the reasons for choosing Gson, aswell as comparing it to two alternative libraries (Jackson and Fastjson):

**Reasons for choosing Gson**


- **Lack of familiarity to any other JSON libraries**: I haven't worked much with JSON, but looking at the options given, I found it the most simple with the most useful features.
- **Simplicity**: Gson is an intuitive and understandable API used for serializing and desrializing Java objects to and from JSON, which saved me alot of time.
- **Lightweight**: Gson is lightweight and didn't add much overhead to the project

**Documentation/Community Support**

- **Lots of Documentation**: Gson has well maintained and easily understandable documentation aswell as there being many tutorials available online. https://github.com/google/gson
- **Active Community**: Because Gson is made by Google, it has a large and active user base with lots of active contributors.
- **StackOverflow/GenerativeAI**: Due to the other points made, Whenever there were things I wanted to add it was easy to find resources on StackOverflow or ChatGPT to find easier ways to solve problems (e.g. prettyPrint vs manual formatting). StackOverflow: https://stackoverflow.com/questions/tagged/gson, chatGPT: https://chatgpt.com

**Technical Aspects**

- Good Performance
- No External Dependencies
- Stability

**Comparison With Alternatives**
-
**Gson vs. Jackson vs. Fastjson**

**Pros**
- 
- **Gson** = {Ease of use, No external Dependencies, Good performance, Documentation, Active Community}
- **Jackson** = {Good performance/efficiency, Features/annotations, Well Maintained}
- **Fastjson** = {Very high Performance, advanced features, Actively maintained}

**Cons**
- 
- **Gson** = {Slightly slower than the others, less feature rich compared to Jackson}
- **Jackson** = {Bigger learning curve, larger library, possible need for additional dependencies}
- **Fastjson** = {Security concerns in the past, Less documentation in english, less community support}

**Documentation**

- Gson: https://github.com/google/gson
- Jackson: https://github.com/FasterXML/jackson
- Fastjson: https://github.com/alibaba/fastjson

**Extra References**

- https://github.com/alibaba/fastjson/wiki/Performance
- https://www.google.com/url?sa=t&source=web&rct=j&opi=89978449&url=https://www.baeldung.com/jackson-vs-gson/&ved=2ahUKEwj9qveSyMGIAxUdslYBHasTJAwQFnoECBoQAQ&usg=AOvVaw2BORR00xBF_Vlch5Bbd7s0

**Conclusion**
- 
In conclusion, and with the specific project as reference the Pros of Gson outweigh the cons, mainly due to it's simplicity, and the fact that this project didn't require the extra performance from the alternatives.

