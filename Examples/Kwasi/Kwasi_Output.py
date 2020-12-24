class Kwasi :
	n1 = -1.0
	n2 = 4.5
	n3 = -5.3
	largest = 0
	a = 2.356
	x = -125.563
	r = -101.23
	w = a + x + r
	intArray = [1,2,3,4,5,6,7,8,9,10]
	cars = ["Volvo", "BMW", "Ford", "Mazda"]
	if n1 >= n2:
		if n1 >= n3:
			largest = n1
		else:
			largest = n3
	else:
		if n2 >= n3:
			largest = n2
		else:
			largest = n3
	print("Example 1 - Largest Number: " , largest)
	print("Example 4 Float addition: ", w)
	print("Example 5 & 6 Arrays int and string")
	for h  in range(0,6):
		print(intArray[h])
	for i  in range(0,3):
		print(cars[i])
	try:
		print("Example 2 - The try statement")
	finally:
		print("Example 3 - The finally statement")
