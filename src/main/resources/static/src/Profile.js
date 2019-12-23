import Menu from "./Menu";
import React, {Component} from "react";
import './Profile.css'
import cookie from "react-cookies";
import {Redirect} from "react-router";
import './Sign.css'

class Profile extends Component{
    constructor(props){
        super(props)
        this.state = { userLog: cookie.load('userLog'), redirect: false}
        this.returnMain = this.returnMain.bind(this);
    }
    returnMain(e){
        this.setState({redirect: true});
    }
    render() {
        if (this.state.redirect === true)
            return <Redirect to={'/'}/>;
        if(!this.state.userLog)
            return (<div className='ParentSign'><div className='Sign'><p className='Fail'>Please, login!</p><button className='Fail' onClick={this.returnMain}>Ok</button></div></div>);
        return(<div>
                <Menu></Menu>
                <div className='Profile'>
                </div>
            </div>
        )
    }
}
export default Profile;