import style from './userInfoTextInput.module.css';

import { ChangeEvent } from 'react';

interface Props {
    type: 'text' | 'password' | 'file';
    id: string;
    label: string;
    value: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

const UserInfoTextInput = ({ type, id, label, value, onChange }: Props) => {
    return (
        <label className={style.label} htmlFor={id}>
            <span>{label}</span>
            <input type={type} id={id} value={value} onChange={onChange} placeholder='' />
        </label>
    );
};

export default UserInfoTextInput;
