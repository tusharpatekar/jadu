import requests

def consume_service():
    url = "http://localhost:5000/api/greet"
    params = {"name": "Distributed Application"}
    response = requests.get(url, params=params)

    if response.status_code == 200:
        print(f"Response from the web service: {response.json()}")
    else:
        print(f"Failed to consume service: {response.status_code}")

if __name__ == "__main__":
    consume_service()
'''
   // 1st Terminal:
   python app.py
   * Serving Flask app 'app'
   * Debug mode: off
   WARNING: This is a development server. Do not use it in a production deployment. Use a production WSGI server instead.
   * Running on all addresses (0.0.0.0)
   * Running on http://127.0.0.1:5000
   * Running on http://192.168.0.117:5000
   Press CTRL+C to quit
   127.0.0.1 - - [03/Dec/2024 16:32:21] "GET /api/greet?name=Distributed+Application HTTP/1.1" 200 -

   // 2nd Terminal:
   python client.py
   Response from the web service: {'message': 'Hello, Distributed Application!'}
'''
