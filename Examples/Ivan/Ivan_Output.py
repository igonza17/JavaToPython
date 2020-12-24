class Ivan :
	print("Forloop function test")
	for i  in range(0,5):
		print(i)
	print("Try and Catch function test")
	try:
		myNumbers = [1, 2, 3]
		print(myNumbers[10])
	except Exception:
		print("Something went wrong.")
	print("Break function test")
	for i  in range(0,10):
		if i == 4:
			break
		print(i)
	print("Continue function test")
	for i  in range(0,10):
		if i == 4:
			continue
		print(i)

