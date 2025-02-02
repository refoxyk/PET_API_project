from flask import Flask, request, jsonify, render_template
from app import app
from klients_service import create_client, update_client, delete_client, get_all_clients,get_client_by_id
from kurjers_service import create_courier, update_courier, delete_courier, get_all_couriers
from pasutijums_service import create_order, update_order, delete_order, get_all_orders

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/api/clients', methods=['GET', 'POST'])
def clients():
    if request.method == 'POST':
        return jsonify(create_client(request.json))
    return jsonify(get_all_clients())
@app.route('/api/clients/<int:klientID>', methods=['GET'])
def get_client(klientID):
	return jsonify(get_client_by_id(klientID))

@app.route('/api/clients/<int:klientID>', methods=['PUT', 'DELETE'])
def client_detail(klientID):
    if request.method == 'PUT':
        return jsonify(update_client(klientID, request.json))
    return jsonify(delete_client(klientID))


@app.route('/api/couriers', methods=['GET', 'POST'])
def couriers():
    if request.method == 'POST':
        return jsonify(create_courier(request.json))
    else:
        return jsonify(get_all_couriers())



@app.route('/api/couriers/<int:kurjerID>', methods=['PUT', 'DELETE'])
def courier_detail(kurjerID):
    if request.method == 'PUT':
        return jsonify(update_courier(kurjerID, request.json))
    return jsonify(delete_courier(kurjerID))


@app.route('/api/orders', methods=['GET', 'POST'])
def orders():
    if request.method == 'POST':
        return jsonify(create_order(request.json))
    return jsonify(get_all_orders())

@app.route('/api/orders/<int:orderID>', methods=['PUT', 'DELETE'])
def order_detail(orderID):
    if request.method == 'PUT':
        return jsonify(update_order(orderID, request.json))
    return jsonify(delete_order(orderID))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
