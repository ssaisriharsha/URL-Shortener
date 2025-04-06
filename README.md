# URL Shortener
A Simple java backend to shorten the long url, similar to bit.ly.
## Key Features
<ul>
  <li>
    Takes any long URL as input, shortens it and returns the short URL.
  </li>
  <li>
    Maintains the record of how many times the URL is clicked, so that the user can have the analytics.
  </li>
  <li>
    For every 5 clicks, the short URL redirects to google.com. This mechanism can be replaced and utilized to display ads for monetization, if needed.
  </li>
  <li>
    CORS is implemented by default. So you can simply download the jar file from the releases, run it and integrate it with your frontend.
  </li>
</ul>

## API Documentation
Following are the endpoints exposed by the application.
<ul>
  <li>
    GET <code>/api/{shortURL}</code>: This endpoints redirects you to the corresponding longURL, if it exists.
  </li>
  <li>
    POST <code>/api/shorten</code>: This endpoint takes long URL as input and gives an unique short URL as output. The data to be provided to this endpoint should be in the following format.
    <code>Content-type: application/json</code>
    JSON format is as follows:
    
      {
        "longURL": "https://www.example.com";
      }

  </li>
</ul>
If no protocol is specified, the API assumes https by default.
