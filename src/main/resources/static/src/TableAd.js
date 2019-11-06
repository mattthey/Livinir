import React, {Component} from 'react';
import Ad from "./Ad";
import './TableAd.css'
class TableAd extends Component{

    constructor(props){
        super(props)
        console.log(this.q);
    }

    initialData = () =>{
        let arr = []
        for (let i=0; i<10; i++)
            arr.push(<li key={i}> <Ad /></li>);
        console.log(arr)
        return arr;
    }
    state={
        ads: this.initialData()
    }
    render() {
        return(<div className="Table">
            <ul>
                {this.state.ads}
            </ul>
        </div>);
    }
}
export default TableAd;