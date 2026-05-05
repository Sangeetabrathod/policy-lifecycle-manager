$body = @{username='admin'; password='admin123'} | ConvertTo-Json -Depth 10
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
$response = Invoke-WebRequest -Uri 'http://localhost:8080/api/auth/login' -Method POST -Body $body -ContentType 'application/json' -UseBasicParsing
$token = $response.Content | ConvertFrom-Json
$env:TOKEN = $token.token
Write-Host "✅ Login successful! Token saved to `$env:TOKEN"
Write-Host "Token preview: $($env:TOKEN.Substring(0,20))..."

