import React from 'react'

export default function Input(props) {
  const {label,error,name,onChange,type}=props;
  const className = error ? 'form-control is-invalid' : 'form-control' ;
  return (
    <div className="form-group">
    <label>{label}</label>
    <input className={className} name={name} type={type} onChange={onChange}/>
    <div id="validationServer03Feedback" className="invalid-feedback">
         {error}
    </div>
</div>
  )
}
