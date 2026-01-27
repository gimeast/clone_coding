'use client';

import style from './signup.module.css';
import { ChangeEvent, useReducer, useState } from 'react';
import ModalHeader from '@/app/(beforeLogin)/_component/ModalHeader';
import UserInfoTextInput from '@/app/(beforeLogin)/_component/UserInfoTextInput';
import SubmitButton from '@/app/(beforeLogin)/_component/SubmitButton';

interface SignupState {
    name: string;
    userId: string;
    password: string;
}

interface SignupAction {
    id: keyof SignupState;
    value: string;
}

const SignupModal = () => {
    const [state, dispatch] = useReducer(reducer, { name: '', userId: '', password: '' });
    const { name, userId, password } = state;
    const [profile, setProfile] = useState<File | null>();

    function reducer(state: SignupState, action: SignupAction) {
        return { ...state, [action.id]: action.value };
    }

    const handleTextChange = (e: ChangeEvent<HTMLInputElement>) => {
        dispatch({ id: e.target.id as keyof SignupState, value: e.target.value });
    };

    const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            setProfile(file);
        }
    };

    return (
        <div className={style.container}>
            <ModalHeader />
            <form className={style.formWrapper}>
                <span className={style.title}>계정을 생성하세요</span>
                <div className={style.inputContainer}>
                    <UserInfoTextInput type='text' id='name' label='이름' value={name} onChange={handleTextChange} />
                    <UserInfoTextInput
                        type='text'
                        id='userId'
                        label='아이디'
                        value={userId}
                        onChange={handleTextChange}
                    />
                    <UserInfoTextInput
                        type='password'
                        id='password'
                        label='비밀번호'
                        value={password}
                        onChange={handleTextChange}
                    />
                    <label htmlFor='profile'>
                        <span>프로필</span>
                        <input type='file' id='profile' onChange={handleFileChange} />
                    </label>
                </div>

                <SubmitButton text='가입하기' />
            </form>
        </div>
    );
};

export default SignupModal;
