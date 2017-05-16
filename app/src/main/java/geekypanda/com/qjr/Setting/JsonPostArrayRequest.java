package geekypanda.com.qjr.Setting;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Abhilash on 3/31/2015.
 */
public class JsonPostArrayRequest extends JsonRequest<JSONObject> {

    JSONArray params;

    public JsonPostArrayRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, JSONArray params) {
        super(Method.POST, url, null, listener, errorListener);
        this.params = params;

    }

    @Override
    public byte[] getBody() {
        if (this.params != null && this.params.length() > 0) {
            return encodeParameters(this.params, getParamsEncoding());
        }
        return null;

    }

    private byte[] encodeParameters(JSONArray params, String paramsEncoding) {
        try {
            return params.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
