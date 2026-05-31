import argparse
import logging
import time
import hmac
import hashlib
import requests
import sys
from urllib.parse import urlencode

# =========================
# CONFIG
# =========================
BASE_URL = "https://testnet.binancefuture.com"
API_KEY = "YOUR_API_KEY"
API_SECRET = "YOUR_API_SECRET"

# =========================
# LOGGING SETUP
# =========================
logging.basicConfig(
    filename="trading_bot.log",
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

# =========================
# VALIDATORS
# =========================
def validate_side(side):
    if side not in ["BUY", "SELL"]:
        raise ValueError("Side must be BUY or SELL")
    return side


def validate_order_type(order_type):
    if order_type not in ["MARKET", "LIMIT"]:
        raise ValueError("Order type must be MARKET or LIMIT")
    return order_type


def validate_quantity(qty):
    try:
        q = float(qty)
        if q <= 0:
            raise ValueError
        return q
    except:
        raise ValueError("Quantity must be a positive number")


def validate_price(price, order_type):
    if order_type == "LIMIT":
        if price is None:
            raise ValueError("Price is required for LIMIT orders")
        try:
            p = float(price)
            if p <= 0:
                raise ValueError
            return p
        except:
            raise ValueError("Invalid price")
    return None


# =========================
# BINANCE CLIENT
# =========================
class BinanceClient:

    def __init__(self, api_key, api_secret):
        self.api_key = api_key
        self.api_secret = api_secret

    def _sign(self, params):
        query_string = urlencode(params)
        signature = hmac.new(
            self.api_secret.encode(),
            query_string.encode(),
            hashlib.sha256
        ).hexdigest()
        return signature

    def place_order(self, symbol, side, order_type, quantity, price=None):
        endpoint = "/fapi/v1/order"

        params = {
            "symbol": symbol,
            "side": side,
            "type": order_type,
            "quantity": quantity,
            "timestamp": int(time.time() * 1000)
        }

        if order_type == "LIMIT":
            params["price"] = price
            params["timeInForce"] = "GTC"

        params["signature"] = self._sign(params)

        headers = {
            "X-MBX-APIKEY": self.api_key
        }

        try:
            logging.info(f"Request: {params}")

            response = requests.post(
                BASE_URL + endpoint,
                headers=headers,
                params=params,
                timeout=10
            )

            data = response.json()

            logging.info(f"Response: {data}")

            if response.status_code != 200:
                raise Exception(data)

            return data

        except requests.exceptions.RequestException as e:
            logging.error(f"Network error: {str(e)}")
            raise Exception("Network error occurred")

        except Exception as e:
            logging.error(f"API error: {str(e)}")
            raise


# =========================
# MAIN EXECUTION
# =========================
def main():
    parser = argparse.ArgumentParser(description="Binance Futures Testnet Trading Bot")

    parser.add_argument("--symbol", required=True)
    parser.add_argument("--side", required=True)
    parser.add_argument("--type", required=True)
    parser.add_argument("--quantity", required=True)
    parser.add_argument("--price", required=False)

    args = parser.parse_args()

    try:
        symbol = args.symbol.upper()
        side = validate_side(args.side.upper())
        order_type = validate_order_type(args.type.upper())
        quantity = validate_quantity(args.quantity)
        price = validate_price(args.price, order_type)

        print("\n=== ORDER REQUEST ===")
        print(f"Symbol: {symbol}")
        print(f"Side: {side}")
        print(f"Type: {order_type}")
        print(f"Quantity: {quantity}")
        if price:
            print(f"Price: {price}")

        client = BinanceClient(API_KEY, API_SECRET)

        response = client.place_order(
            symbol=symbol,
            side=side,
            order_type=order_type,
            quantity=quantity,
            price=price
        )

        print("\n=== ORDER RESPONSE ===")
        print(f"Order ID: {response.get('orderId')}")
        print(f"Status: {response.get('status')}")
        print(f"Executed Qty: {response.get('executedQty')}")
        print(f"Avg Price: {response.get('avgPrice', 'N/A')}")

        print("\n✅ Order placed successfully")

    except Exception as e:
        print(f"\n❌ Error: {str(e)}")
        logging.error(f"Execution failed: {str(e)}")
        sys.exit(1)


if __name__ == "__main__":
    main()