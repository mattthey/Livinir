import React, {Component} from 'react';
import './Ad.css';
class Ad extends Component{

    constructor(props){
        super(props);
        // this.id = ad.id;
        // this.address = ad.address;
        // this.area = ad.area;
        // this.city = ad.city;
        // this.description = ad.description;
        // this.lease_date =  ad.lease_date;
        // this.owner_id = ad.owner_id;
        // this.name =  JSON.parse(fetch('/name/user', {id: this.id}).text()).first_name;
        this.state={
            name: 'Name',
            area: 'Ekb',
            lease_date:'02.10.2010'
        }
    }
    render() {
        return ( <div className="Ad">
            <h3> {this.state.name} </h3>
            {this.state.area} <br/>
            {this.state.lease_date}
            <button className="detailed">more detailed</button>
        </div>);
    }
}
export default Ad;