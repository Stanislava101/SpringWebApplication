<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Product</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>
	
    <div class="container my-5" style="padding-top: 30px;">
    
        <h3>Product</h3>
        
        
        <div class="card">
            <div class="card-body">
                <div class="col-md-10">
                
                    <form action="/createProduct" object="${employee}" method="post">
                    
                    	<div class="row">
                        	
                            <div class="form-group col-md-8">
                                <label for="type" class="col-form-label">Type</label> 
                                <input type="text" field="${type}" name="type" class="form-control" 
                                            id="firstName" placeholder="Type" />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="model" class="col-form-label">Model</label> 
                                <input type="text" name="model" class="form-control" 
                                            id="lastName" placeholder="Model" />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="quantity" class="col-form-label">Quantity</label> 
                                <input type="text" name="quantity" class="form-control" 
                                            id="email" placeholder="Quantity" />
                            </div>
                                                        <div class="form-group col-md-8">
                                <label for="price" class="col-form-label">Price</label> 
                                <input type="text" field="price" name="price" class="form-control" 
                                            id="firstName" placeholder="Price" />
                            </div>

<input type="hidden" name="hide" field="id" value="info">

        <input type="submit" value="Submit">
    
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>