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
                    <div className='DataProfile'>
                        <h3>Data_user</h3>
                        <p>wqeqwsfeww: dsewdwe</p>
                        <p>wqeqwsfeww: wwrqewae</p>
                        <p>wqeqwsfeww: qwewqeewq</p>
                        <p>wqeqwsfeww: wqeqweawE</p>
                    </div>
                    <form className='DataAd'>
                        <h3>Create_Ad</h3>
                        <input type='email' name='email' placeholder='Enter your email' defaultValue='test@gmail.com'/>
                        <input type='text' name='login' placeholder='Enter your login' defaultValue='user'/>
                        <input type='text' name='firstName' placeholder='Enter your First Name' defaultValue='user'/>
                        <input type='text' name='lastName' placeholder='Enter your Last Name' defaultValue='user'/>
                        <input type='password' name='password' placeholder='Enter password' defaultValue='123456'/>
                        <input type='date' name='dateOfBirth' placeholder='Enter your birthday' defaultValue='2019-01-19'/>
                        <br/>
                        <textarea name="content" placeholder='Enter your additional'></textarea>
                        <br/>
                        <p> Your gender:
                            <input type="radio" name="Sex" value='male' defaultChecked={true}/> Male
                            <input type="radio" name="Sex" value='female'/> Female
                        </p>
                        <button>Create</button>
                    </form>
                </div>
            </div>
        )
    }
}
export default Profile;