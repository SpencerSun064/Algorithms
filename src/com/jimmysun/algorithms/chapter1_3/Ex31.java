import java.util.Iterator;
  4 
  5 import edu.princeton.cs.algs4.StdOut;
  6 
  7 /**
  8  * 
  9  * @author Lucas.Li
 10  *
 11  * @param <Item>
 12  * 
 13  * @Content 双向链表的构造
 14  */
 15 public class BothwayLinkedListStack<Item> implements Iterable<Item> {
 16 
 17     private int N;
 18     private DoubleNode first;
 19     private DoubleNode last;
 20 
 21     /**
 22      * 
 23      * @author Lucas.Li 构造嵌套类
 24      * 
 25      */
 26     private class DoubleNode {
 27         Item item;
 28         DoubleNode next;
 29         DoubleNode prev;
 30     }
 31 
 32     /**
 33      * 
 34      * @return 链表大小
 35      */
 36     public int size() {
 37         return N;
 38     }
 39 
 40     /**
 41      * 
 42      * @return 链表是否为空
 43      */
 44     public boolean isEmpty() {
 45         return N == 0;
 46     }
 47 
 48     /**
 49      * 在表头添加元素
 50      * 
 51      * @param item
 52      */
 53     public void push(Item item) {
 54         DoubleNode oldFirst = first;
 55         first = new DoubleNode();
 56         first.item = item;
 57         first.next = oldFirst;
 58         first.prev = null;
 59         if (N == 0) {
 60             last = first;
 61         } else {
 62             oldFirst.prev = first;
 63         }
 64         N++;
 65     }
 66 
 67     /**
 68      * 
 69      * @return 表头弹出的元素值
 70      */
 71     public Item pop() {
 72         if (N == 0) {
 73             try {
 74                 throw new Exception("There is none on stack!");
 75             } catch (Exception e) {
 76                 // TODO Auto-generated catch block
 77                 e.printStackTrace();
 78             }
 79         }
 80         Item item = first.item;
 81         first = first.next;
 82         first.prev = null;
 83         N--;
 84         return item;
 85     }
 86 
 87     /**
 88      * 表头添加元素
 89      * 
 90      * @param item
 91      */
 92     public void insertHead(Item item) {
 93         DoubleNode node = new DoubleNode();
 94         node.item = item;
 95         node.prev = null;
 96         node.next = first;
 97         first.prev = node;
 98         first = new DoubleNode();
 99         first = node;
100         N++;
101     }
102 
103     /**
104      * 表尾添加元素
105      * 
106      * @param item
107      */
108     public void insertTail(Item item) {
109         DoubleNode node = new DoubleNode();
110         node.item = item;
111         node.prev = last;
112         node.next = null;
113         last.next = node;
114         last = new DoubleNode();
115         last = node;
116         N++;
117     }
118 
119     /**
120      * 指定结点前插入元素
121      * 
122      * @param item
123      * @param node
124      */
125     public void insertBeforeNode(Item item, DoubleNode node) {
126         if (isFirst(node)) {
127             insertHead(item);
128             N++;
129         } else {
130             DoubleNode newNode = new DoubleNode();
131             newNode.item = item;
132             newNode.prev = node.prev;
133             newNode.next = node;
134             node.prev.next = newNode;
135             node.prev = newNode;
136             N++;
137         }
138     }
139 
140     /**
141      * 指定结点后插入元素
142      * 
143      * @param item
144      * @param node
145      */
146     public void insertAfterNode(Item item, DoubleNode node) {
147         if (isLast(node)) {
148             insertTail(item);
149             N++;
150         } else {
151             DoubleNode newNode = new DoubleNode();
152             newNode.item = item;
153             newNode.prev = node;
154             newNode.next = node.next;
155             node.next.prev = newNode;
156             node.next = newNode;
157             N++;
158         }
159     }
160 
161     /**
162      * 删除指定结点
163      * @param node
164      */
165     public void deleteNode(DoubleNode node){
166         if (isFirst(node)){
167             deleteHead();
168             N--;
169         } else if (isLast(node)) {
170             deleteTail();
171             N--;
172         } else {
173             node.next.prev = node.prev;
174             node.prev.next = node.next;
175             node.prev = null;
176             node.next = null;
177             N--;
178         }
179     }
180 
181     /**
182      * 删除表头元素
183      */
184     public void deleteHead() {
185         if (N == 0) {
186             try {
187                 throw new Exception("There is none on stack!");
188             } catch (Exception e) {
189                 // TODO Auto-generated catch block
190                 e.printStackTrace();
191             }
192         }
193         first = first.next;
194         first.prev = null;
195         N--;
196     }
197 
198     /**
199      * 删除表尾元素
200      */
201     public void deleteTail() {
202         if (N == 0) {
203             try {
204                 throw new Exception("There is none on stack!");
205             } catch (Exception e) {
206                 // TODO Auto-generated catch block
207                 e.printStackTrace();
208             }
209         }
210         last = last.prev;
211         last.next = null;
212         N--;
213     }
214 
215     /**
216      * 
217      * @param m
218      * @return 指定结点(从表头数起第m个结点)
219      */
220     public DoubleNode getNode(int m) {
221         if (m > N) {
222             try {
223                 throw new Exception("Not enough on stack! Return the last one!");
224             } catch (Exception e) {
225                 // TODO Auto-generated catch block
226                 e.printStackTrace();
227             }
228             return last;
229         }
230         DoubleNode node = new DoubleNode();
231         node = first;
232         int i = 1;
233         while (i < m) {
234             node = node.next;
235             i++;
236         }
237         return node;
238     }
239 
240     /**
241      * 
242      * @param node
243      * @return 结点元素的值
244      */
245     public Item getItem(DoubleNode node) {
246         return node.item;
247     }
248 
249     /**
250      * 
251      * @return 链表首结点
252      */
253     public DoubleNode getFirst() {
254         return first;
255     }
256 
257     /**
258      * 
259      * @return 链表尾结点
260      */
261     public DoubleNode getLast() {
262         return last;
263     }
264 
265     /**
266      * 
267      * @param node
268      * @return 判断结点是否为首结点
269      */
270     public boolean isFirst(DoubleNode node) {
271         if (node.prev == null) {
272             return true;
273         } else {
274             return false;
275         }
276     }
277 
278     /**
279      * 
280      * @param node
281      * @return 判断结点是否为尾结点
282      */
283     public boolean isLast(DoubleNode node) {
284         if (node.next == null) {
285             return true;
286         } else {
287             return false;
288         }
289     }
290 
291     /**
292      * 遍历器
293      */
294     @Override
295     public Iterator<Item> iterator() {
296         // TODO Auto-generated method stub
297         return new ListIterator();
298     }
299 
300     /**
301      * 扩展实现遍历器
302      * 
303      * @author Administrator
304      *
305      */
306     private class ListIterator implements Iterator<Item> {
307 
308         private DoubleNode current = first;
309 
310         @Override
311         public boolean hasNext() {
312             // TODO Auto-generated method stub
313             return current != null;
314         }
315 
316         @Override
317         public Item next() {
318             // TODO Auto-generated method stub
319             Item item = current.item;
320             current = current.next;
321             return item;
322         }
323 
324         @SuppressWarnings("unused")
325         public DoubleNode getCurrent() {
326             return current;
327         }
328 
329     }
330 
331 }