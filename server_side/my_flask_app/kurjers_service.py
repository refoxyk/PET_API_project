from app import db, Kurjers

def create_courier(data):
   
    existing_email = Kurjers.query.filter_by(email=data['email']).first()
    if existing_email:
        return {'message': 'Kurjers ar sadu e-pasta adresi jau eksiste'}, 400

  
    if 'phoneNumber' in data and data['phoneNumber']:
        existing_phone = Kurjers.query.filter_by(phoneNumber=data['phoneNumber']).first()
        if existing_phone:
            return {'message': 'Kurjers ar sadu telefona numuru jau eksiste'}, 400

  
    new_courier = Kurjers(
        fullName=data['fullName'],
        email=data['email'],
        phoneNumber=data['phoneNumber'],
        batteryLevel=data['batteryLevel'],
        available=data.get('available', True),
        locationLatitude=data['locationLatitude'],
        locationLongitude=data['locationLongitude']
    )
    db.session.add(new_courier)
    db.session.commit()
    return {'message': 'Kurjers izveidots', 'kurjerID': new_courier.kurjerID}

def update_courier(kurjerID, data):
    courier = Kurjers.query.get(kurjerID)
    if not courier:
        return {'message': 'Kurjers nav atrasts'}, 404

    # Проверка на уникальность email, если он обновляется
    if 'email' in data and data['email'] != courier.email:
        existing_email = Kurjers.query.filter_by(email=data['email']).first()
        if existing_email:
            return {'message': 'Kurjers ar sadu e-pasta adresi jau eksiste'}, 400

    # Проверка на уникальность телефона, если он обновляется
    if 'phoneNumber' in data and data['phoneNumber'] != courier.phoneNumber:
        existing_phone = Kurjers.query.filter_by(phoneNumber=data['phoneNumber']).first()
        if existing_phone:
            return {'message': 'Kurjers ar sadu telefona numuru jau eksiste'}, 400

    # Обновление данных курьера
    courier.fullName = data.get('fullName', courier.fullName)
    courier.email = data.get('email', courier.email)
    courier.phoneNumber = data.get('phoneNumber', courier.phoneNumber)
    courier.batteryLevel = data.get('batteryLevel', courier.batteryLevel)
    courier.available = data.get('available', courier.available)
    courier.locationLatitude = data.get('locationLatitude', courier.locationLatitude)
    courier.locationLongitude = data.get('locationLongitude', courier.locationLongitude)

    db.session.commit()
    return {'message': 'Kurjera informacija atjauninata'}

def delete_courier(kurjerID):
    courier = Kurjers.query.get(kurjerID)
    if not courier:
        return {'message': 'Kurjers nav atrasts'}, 404

    db.session.delete(courier)
    db.session.commit()
    return {'message': 'Kurjers nonemts'}

def get_all_couriers():
    couriers = Kurjers.query.all()
    return [
        {
            'kurjerID': c.kurjerID,
            'fullName': c.fullName,
            'email': c.email,
            'phoneNumber': c.phoneNumber,
            'batteryLevel': c.batteryLevel,
            'available': c.available
        }
        for c in couriers
    ]
