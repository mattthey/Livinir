import React,{Component} from 'react';
import { Redirect } from 'react-router';
import './Sign.css'

class SignIn extends Component{
    constructor(props){
        super(props);
        this.state = {
            redirect: false
        };
        this.sentData = this.sentData.bind(this);
    }
    sentData(e) {
        console.log(e.target.elements)
        this.setState({ redirect: true })
    }

    render() {
        if (this.state.redirect) {
            this.state.redirect = false;
            return <Redirect to='/'/>;
        }
        return(<div className='ParentSign'>
            <form onSubmit={this.sentData} className='Sign SignIn'>
                <input type='text' name='Email' placeholder='Enter your email'/>
                <input type='text' name='Password' placeholder='Enter your password'/>
                <button >Sign In</button>
            </form>
        </div>)
    }
}
export default SignIn