<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
</head>

<body>
<div id="myQrcode"></div>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
    //jQuery('#qrcode').qrcode("this plugin is great");
    jQuery("#myQrcode").qrcode({
        text: "${codeUrl}",
    });
    $(function () {
        //timer
        setInterval(function () {
            console.log('start query payment status')
            $.ajax({
                url:'/pay/queryByOrderId',
                data: {
                    'orderId': '123456'
                },
                success: function (result) {
                    console.log(result)
                },
                error: function (result) {
                    alert(result)
                }
            })
        },2000)
    })
</script>
</body>
</html>
