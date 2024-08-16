<!DOCTYPE html>
<html>
<head>
    <title>Weather Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFA500;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #4682b4;
            margin-top: 20px;
        }

        .divout {
            max-width: 600px;
            margin: 100px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            font-size: 1.2em;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="number"] {
            font-size: 1em;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
            max-width: 300px;
        }

        input[type="submit"] {
            font-size: 1em;
            padding: 10px 20px;
            background-color: #4682b4;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #4169e1;
        }

        @media (max-width: 600px) {
            .container {
                padding: 10px;
            }

            input[type="text"],
            input[type="number"] {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>

	<%  String errorMessage = request.getParameter("error");
        if (errorMessage != null && !errorMessage.isEmpty()) { 
   %>
        <h3 style="color: #45474B;"><%= errorMessage %></h3
        >
   <% } %>
   
	<!-- form to get username and postalcode  -->
	<div class="divout">
    <h2>Welcome, Please enter Username and Postalcode to get weather details</h2>
    <form action="submit" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required>
        <br>
        <label for="postalcode">Postal Code:</label>
        <input type="text" name="postalcode"  id="postalcode" maxlength=5 required>
        <br>
        <input type="submit" value="Submit">
    </form>
    </div>
  
</body>
</html>
