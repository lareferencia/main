<?php
/**
 * Base class to enable sharing of common methods between SMS subclasses
 *
 * PHP version 5
 *
 * Copyright (C) Villanova University 2009.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * @category VuFind
 * @package  Support_Classes
 * @author   Ronan McHugh <vufind-tech@lists.sourceforge.net>
 * @license  http://opensource.org/licenses/gpl-2.0.php GNU General Public License
 * @link     http://vufind.org/wiki/system_classes Wiki
 */
require_once 'Interface.php';

/**
 * Base class to enable sharing of common methods between SMS subclasses
 *
 * @category VuFind
 * @package  Support_Classes
 * @author   Ronan McHugh <vufind-tech@lists.sourceforge.net>
 * @license  http://opensource.org/licenses/gpl-2.0.php GNU General Public License
 * @link     http://vufind.org/wiki/system_classes Wiki
 */
abstract class SMS_Base implements SMSInterface
{
    protected $smsConfig;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->smsConfig = getExtraConfigArray('sms');
    }

    /**
     * Filter bad characters from a phone number
     *
     * @param string $num Phone number to filter
     *
     * @return string
     */
    protected function filterPhoneNumber($num)
    {
        $filter = isset($this->smsConfig['General']['filter'])
            ? $this->smsConfig['General']['filter'] : '-.() ';
        return str_replace(str_split($filter), '', $num);
    }

    /**
     * Get validation type for phone numbers
     *
     * @return string
     */
    public function getValidationType()
    {
        // Load setting from config; at present, only US is implemented in templates
        $validation = isset($this->smsConfig['General']['validation'])
            ? $this->smsConfig['General']['validation'] : 'US';
        return $validation;
    }
}