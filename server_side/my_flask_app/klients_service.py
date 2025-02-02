from app import db, Klients

def create_client(data):
    existing_email = Klients.query.filter_by(email=data['email']).first()
    if existing_email:
        return {'message': 'Klients ar sadu e-pasta adresi jau eksiste'}, 400

    if 'phoneNumber' in data and data['phoneNumber']:
        existing_phone = Klients.query.filter_by(phoneNumber=data['phoneNumber']).first()
        if existing_phone:
            return {'message': 'Klients ar sadu telefona numuru jau eksiste'}, 400

    new_client = Klients(
        fullName=data['fullName'],
        email=data['email'],
        phoneNumber=data.get('phoneNumber'),
        address=data.get('address'),
        createdAt=data.get('createdAt')  # Fixed syntax error here
    )
    db.session.add(new_client)
    db.session.commit()

    return {'message': 'Klients izveidots', 'klientID': new_client.klientID}

def update_client(klientID, data):
    client = Klients.query.get(klientID)
    if not client:
        return {'message': 'Klients nav atrasts'}, 404

    client.fullName = data.get('fullName', client.fullName)
    client.email = data.get('email', client.email)
    client.phoneNumber = data.get('phoneNumber', client.phoneNumber)
    client.address = data.get('address', client.address)

    db.session.commit()
    return {'message': 'Klienta informacija atjauninata'}

def delete_client(klientID):
    client = Klients.query.get(klientID)
    if not client:
        return {'message': 'Klients nav atrasts'}, 404

    db.session.delete(client)
    db.session.commit()
    return {'message': 'Klients izdzests'}

def get_all_clients():
    clients = Klients.query.all()
    return [
        {
            'klientID': c.klientID,
            'fullName': c.fullName,
            'email': c.email,
            'phoneNumber': c.phoneNumber,
            'address': c.address,
            'createdAt': c.createdAt  # Fixed missing comma here
        }
        for c in clients
    ]

def get_client_by_id(klientID):  # Fixed indentation issue here
    client = Klients.query.get(klientID)
    if not client:
        return {'message': 'Klients nav atrasts'}, 404

    return {
            'klientID': client.klientID,
            'fullName': client.fullName,
            'email': client.email,
            'phoneNumber': client.phoneNumber,
            'address': client.address,
            'createdAt': client.createdAt  # Fixed indentation and changed from `clients` to `client`
        }
    
