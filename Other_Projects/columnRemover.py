import os
import csv

input_file = open('C:\\Users\\lenovo\\school_projects\\Other_Projects\\testTelem.csv', 'r', newline='')
reader = csv.reader(input_file)

output_file = open('C:\\Users\\lenovo\\school_projects\\Other_Projects\\testTelemStripped.csv', 'w', newline='')
writer = csv.writer(output_file)

for row in reader:
    removed_columns = []
    for i in range(len(row)):
        if i % 2 == 0:
            removed_columns.append(row[i])
    writer.writerow(removed_columns)

input_file.close()
output_file.close()

print("Write complete")