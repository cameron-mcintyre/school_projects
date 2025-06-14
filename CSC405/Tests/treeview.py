import tkinter as tk
from tkinter import ttk

line1 = "hello"
line2 = "hi friend"
line3 = "this is treeview"

window = tk.Tk()
window.resizable(width = 1, height = 1)

treev = ttk.Treeview(window, selectmode = 'browse')
treev.pack(side = 'right')
verscrlbar = ttk.Scrollbar(window, orient="vertical", command=treev.yview)

verscrlbar.pack(side='right', fill = 'both') #x, y, both, neither
treev.configure(xscrollcommand=verscrlbar.set)
treev['show'] = 'headings'

treev['columns'] = ('1','2')
treev.column('1', width=90, anchor='nw')  #c for center, or coordinates
treev.heading('1', text ='String')

treev.column('2', width=90, anchor='sw')  #c for center, or coordinates se sw n s etc
treev.heading('2', text ='Gnirts')

treev.insert("", 'end', text = 'L1', values =(line1, line3))
treev.insert("", 'end', text = 'L2', values =(line2, line2))
treev.insert("", 'end', text = 'L3', values =(line3, line1))

window.mainloop()
