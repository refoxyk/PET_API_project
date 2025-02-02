from app import db, Pasutijums

def create_order(data):
    new_order = Pasutijums(
        klientID=data['klientID'],  
        kurjerID=data.get('kurjerID'),  
        deliveryAddress=data['deliveryAddress'],
        deliveryFee=data['deliveryFee'],
        status=data.get('status', 'Pending')
    )
    db.session.add(new_order)
    db.session.commit()
    return {'message': 'Pasutijums izveidots', 'orderID': new_order.orderID}

def update_order(orderID, data):
    order = Pasutijums.query.get(orderID)
    if not order:
        return {'message': 'Pasutijums nav atrasts'}, 404

    order.deliveryAddress = data.get('deliveryAddress', order.deliveryAddress)
    order.deliveryFee = data.get('deliveryFee', order.deliveryFee)
    order.status = data.get('status', order.status)
    order.kurjerID = data.get('kurjerID', order.kurjerID) 

    db.session.commit()
    return {'message': 'Pasutijuma informacija atjauninata'}

def delete_order(orderID):
    order = Pasutijums.query.get(orderID)
    if not order:
        return {'message': 'Pasutijums nav atrasts'}, 404

    db.session.delete(order)
    db.session.commit()
    return {'message': 'Pasutijums dzests'}

def get_all_orders():
    orders = Pasutijums.query.all()
    return [{'orderID': o.orderID, 'klientID': o.klientID, 'kurjerID': o.kurjerID, 'deliveryAddress': o.deliveryAddress, 'deliveryFee': o.deliveryFee, 'status': o.status} for o in orders]
