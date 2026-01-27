'use client';

import style from './login.module.css';
import ModalHeader from '@/app/(beforeLogin)/_component/ModalHeader';
import UserInfoTextInput from '@/app/(beforeLogin)/_component/UserInfoTextInput';
import { ChangeEvent, useState } from 'react';
import SubmitButton from '@/app/(beforeLogin)/_component/SubmitButton';

const LoginModal = () => {
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');

    const handleTextInput = (e: ChangeEvent<HTMLInputElement>, type: 'userId' | 'password') => {
        switch (type) {
            case 'userId':
                setUserId(e.target.value);
                break;
            case 'password':
                setPassword(e.target.value);
                break;
        }
    };

    return (
        <div className={style.container}>
            <ModalHeader />
            <form className={style.formWrapper}>
                <span className={style.title}>로그인하세요</span>
                <div className={style.inputContainer}>
                    <UserInfoTextInput
                        type='text'
                        id='userId'
                        label='아이디'
                        value={userId}
                        onChange={e => handleTextInput(e, 'userId')}
                    />
                    <UserInfoTextInput
                        type='password'
                        id='password'
                        label='비밀번호'
                        value={password}
                        onChange={e => handleTextInput(e, 'password')}
                    />
                </div>

                <SubmitButton text='로그인하기' />
            </form>
        </div>
    );
};

export default LoginModal;
