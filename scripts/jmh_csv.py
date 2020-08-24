import csv

with open('build/reports/jmh/results.csv', newline='') as f:
    rows = []
    for r in csv.reader(f):
        rows.append(r)

rows = rows[1:]
params = {}
names = {}
matrix = {}
for r in rows:
    short_name = r[0][max(0, r[0].rfind('.')):]
    names[short_name] = True
    key = int(r[-1])
    params[key] = True
    if key not in matrix:
        matrix[key] = {}
    matrix[key][short_name] = float(r[4])

with open('chart.csv', 'wt') as f:
    writer = csv.writer(f)
    writer.writerow([rows[0][-1]] + names.keys())
    for p in sorted(params.keys()):
        r = [p]
        for t in sorted(names.keys()):
            r.append(matrix[p][t])
        writer.writerow(r)
